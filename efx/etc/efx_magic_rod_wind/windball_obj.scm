	� G   �       �W_oEv���p8�tW_��s�&2F
����$
����>�|޽ܭ;�%\���	!���o�W$�w�J߂�����)iU�	�η;3;;3����;�DP֋5ϱ�X8��F!��������?
y$
�Z��k��V���ן��R>����3PɄ�Kj9�->L�Rjs��=(�Ѝ�͍R���}AG�|]Dcjj�k��Љc�F�K�?٧�F��9k�ڙ�zZK����^����
V1�ǫ��>�V���E�c��w�U7A�l���9�ꐂo��;]+.���SF]��;AL���E�,�e\�����T�'T����Z��k�RoS�]'�:�v��Ͷ@��{� �^;F�:�pa@�jW��C�D����>���䙚��3��gBL��~��̮�)���E�͠R��)IaW�X����@�-%�@�jV�"�Ī5��Sp�X�R.������%�̲o�P�����n�����p)�����4R��p�ܬ���r�6�|U����Wgk��"�QdE���&KYB�f�vRe��%(�����nٹ���.�4ҥ9�"uWI�*Ii��J@\5L��p���1Y^]W�%��t�l�"� y���Y7�C�^R�rx#��AQ3�ͪ��j�}}�[�o�[���M��[�T�_X�) RWFi��B�-���e�Y���(/D��-�� (�D`�	P2Po�<��Z��HLИ ��A�I�������O��Lz��ł�	q>� +ۣA�nЄ���pj<��5���G�^�*�@ ���
�:F����q8[�)����5�*X�H~�c᥍�jÔ�6E�x��G��(�	N@XG+��v�����#C3�;	�8��rN��l����JA�5��T�Hf"��Ԉ���`>t��� �=����d��H��(���V@�'�ajSP���j���W/��&��u�0��|zT8$�Xn�����տz����O_�\��6)|w1�����y���7?,��.�|��?[<�����K;�m?� '�upǔN��$7����Fji��~c�3�`��q�z&S�:p.�o�B$Ϭ%�k�(N����L汋�]�Q�k�%*n^/�B����og�x�ۉx��yV�yv�f'���8���Pw�.e�a<�h�#���+<⽣������U}���M{���[Md)��2��D���\���5�� b���sMz;-H^��|ZML��P%���X���N�C+)&&
�|��N�8M[C�
M����T�w1�3sn�����O   �  Lga2/stage/AmpedScript; e start broken 
wind_arrow lockItemScript (ZZ)V
 f j k
 H 0 3D /efx/tornado/wind_line.scm�    ����    ?�  ?�  ?�              D /efx/kirakira/03_b.ptc�            ?�  ?�  ?�               D /efx/flash/glow01_blu19.ptc�            ?L��?L��?L��             D /efx/tornado/wind.scm�            ?L��?L��?L��            D /efx/ripple/ripple05_blu3.ptc�            >���>���>���              rndc (F)F
 H s t Lkotori/data/KGLGenerationData; v hpb (FFF)V
 w x yA    scale (F)V
 w | }?�  >�� sy	 w � ?333=�\) generate A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d � �C4  =��� /(Ljava/lang/Object;)Lkotori/gfx/KGLScriptModel;
 d � �>���?L��>L��=L�� 
soccer_bluB�   lockTimeCount (Z)V
 f � �D /efx/tornado/wind.scm�            @   @   @                D /efx/flash/glow01_blu19.ptc�            ?L��?L��?L��             D /efx/kirakira/03_b.ptc�            ?�  ?�  ?�               ?��A�  B    kill
 f � 1 
stopScript
 f � 1 getArgv ()Ljava/lang/Object;
 f � � .(Lkotori/geom/Vector3D;)Lkotori/geom/Vector3D;
 H  �-             	 � R  getPosition ()Lkotori/geom/Position;
 f � � sub
 � � � cos
 H � t sin
 H � t	 � Q  Lkotori/geom/Position; � dir	 � � 	 � S  (FFF)Lkotori/geom/Vector3D;
 � + � Lkotori/geom/KMath; � getDir (Lkotori/geom/Vector3D;)F
 � � � R2D
 � � t Ljava/lang/Math; � round (F)I
 � � � length ()F
 � � � (FF)F
 � � ��� kotori.geom.Quaternion?�               Lkotori/geom/Quaternion; � clone
 � � � getHPBQuaternionD 5(FFFLkotori/geom/Quaternion;)Lkotori/geom/Quaternion;
 � � � 	playSound
 d � 3 	genoffset 1(Lkotori/geom/Vector3D;Lkotori/geom/Quaternion;)V
 d � � attack E(Lkotori/geom/Vector3D;Lga2/stage/atk/AttackParam;)Lga2/stage/Attack;
 f � �-            ��� �D %/efx/etc/efx_magic_rod_wind/arrow.scm�            >L��>L��>L��            � hit_b2 �� ,    '�       ,�>�        D /efx/tornado/wind.scm�            ?�ff?�ff?�ff    B����~�0 D /efx/flash/glow01_blu19.ptc�            @   @   @                D /efx/kirakira/03_b.ptc�            ?L��?L��?L��B�y�B��#A�  Lkotori/scp/KotoriScript; � realtime
 � � 2
 H 0 1 existAttack ()Z
 f � � f  g 1 4*� lW*d� mWnLoMpNq:r:*� lW6�+{� u{� u{� u� zW+�� ub� ~W+��� ub� �*+g� �Wp� *,�� u�� u�� u� zW,b�� ub� ~W*,� �W��� ub� ~W*� �W6� {-��� ub� ~W*-� �Wp� Sb�� ub� ~W�� u8�� u8	�� u8
	
� zW*�� �W�b	
� zW*�� �W*2� mW�W����W����  h 1  �*� lW*� �W*� mW�L�M�N,� ~W*,� �W-�� u�� u�� u� zW-�� ~W*-� �W6� >�� u8�� u8+� zW+��{nb� ~W*+�� �W*� mW�W���*� �W*� �W�  i 1 �*� lW*� �W*� mW*� �L+� +� �L� �L+Y� �bb� �*� �M+,� �W+� �,� �� �j+� �,� �� �jfF+� �,� �� �j+� �,� �� �jb8+� �8+%� �W+� �� �� Æ8+� �� ȸ �� �Zd�8�:� �:� �W*e� �W*� �W*�ڶ �W�:	�:
�:
� ~W*
� �W
� ~W*
� �W�� u�� u�� u� zW�� ~W*� �W6� D�� u8�� u8	�b� zW	��{nb� ~W*	�� �W*� mW�W���*� �W*� ��`6� *� �� � *� �W*� ���*� �W*� �W� ��(�                        �  