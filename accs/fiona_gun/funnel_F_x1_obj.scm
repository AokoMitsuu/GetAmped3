	� G   ��       �V{O�F�;�|�m�*��1�^e)W�J)� ����Y'��^k����|���7��]^E핇�ߌw懯d-H:���n.����$#�i�i&����-�d��b�q�α��n� ,y�����	+�Y
�T� ��8�t�
#������t:;���Fh��<����Y�Ҹct��N���V+f�� ���� =$�o�����w��$FQ��p��$%�xJ�F���8J�8�YwE�ؑ�sg�i霈���=�&��
��d�RaL6�W;�����%��v�aޏ(K�lQ�}-?��<L����6�_gk��,�1��
�FR�|�����9驫	[�MUČ;t���Q��v�
�V�YH�������T5�8R ���%"���������s�! �\x��qar��,-`9Rd<t�O�[{���RXK);�ۑ#�ǣf�=<(��n���lER� "��ݠQQ��)sGG�
d��ߊ�"!��d �טC�A�TFX�ъ��t�<��\��B�{��#��M����p�� �W����Q\��
� PRj�%�ʀ���NRB3IQ>[�s,�.g@z������N(w�B���O��������1
P�h<x�u�ӽ�?�_Y�lm��V���4�����\m������^C��ćDOBU3I��V��t!�NV�&$ן{�h�~��cO��\D�ҵ;�U̠��aN�mL��]G9Hh�{���Ux-��Zes����@����L����ŵ���W�k"\�0f���ZI&���}M8�@� �
h����CE�ؓ�p3n� Y�˩������0=���ř�����c%_2a�F��"8��ona!p�rvn����11�d����������v
��@o�vr��P��C�m�P�8�7�p[�]Z�%y��{�f���e�H6��w�C~vp�_ͦ/�1e��UpiUn�
�� �L��%	WM�"@S�in�ײ6
�G(�RT�Cu��:q��=yf���N�//���$�2/-&�[?�q��/bv�f%���L�/�ʗr4p����(��}{�{U��������/Lf�U5_4����~�)/g���F^f�����U�eT^�ɢ����m�~�!3@:���y�*?`VW�Д�Z�[�`���Z\��]�Z�   ~  Lga2/stage/AmpedScript; e PROCESS_MAX 
ATTACK_MAX start process approachtarget broken lockItemScript (ZZ)V
 f m n getGenerate (I)Lkotori/gfx/KGLScriptModel;
 d p q Lkotori/gfx/KGLScriptModel; s kill
 t u 1
 H 0 3 setShadowState
 d x 3 tempInt (I)Ljava/lang/Integer;
 d z { generate A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d } ~D /accs/fiona_gun/funnel_l.scm�            ?�  ?�  ?�               funnel_F_x2 clearGenerate
 d � 1 setGenerate 8(Lkotori/gfx/KGLScriptModel;)Lkotori/gfx/KGLScriptModel;
 d � � temp ()Ljava/lang/Object;
 d � � Ljava/lang/Integer; � intValue
 � � 2��� setJudgmentApproachTarget (F)V
 f � �A    clearJudgmentKind
 f � 1 getArgv
 f � � getPosition ()Lkotori/geom/Position;
 f � �  sub .(Lkotori/geom/Vector3D;)Lkotori/geom/Vector3D;
 � � � Lkotori/geom/KMath; � getDir (Lkotori/geom/Vector3D;)F
 � � � R2D (F)F
 � � � Ljava/lang/Math; � round (F)I
 � � � ()F
 f � �C4  C�  �4  	 � R  length
 � � � (FF)F
 � � � min
 � � �B4  �� kotori.geom.Quaternion?�               Lkotori/geom/Quaternion; � clone
 � � � getHPBQuaternionD 5(FFFLkotori/geom/Quaternion;)Lkotori/geom/Quaternion;
 � � � rotate (Lkotori/geom/Quaternion;I)V
 t � � 	playSound (IIFF)V
 d � � (II)V
 d � � startScript (Ljava/lang/String;)Z
 t � � funnel_shot2 	genoffset 1(Lkotori/geom/Vector3D;Lkotori/geom/Quaternion;)V
 d � � attack E(Lkotori/geom/Vector3D;Lga2/stage/atk/AttackParam;)Lga2/stage/Attack;
 f � �-            �� �D $/accs/fiona_gun/funnel_F_x2_shot.scm�            ?�  ?�  ?�              � hit_fiona_gun  d� ,    FP�       ,�>�         (Z)V
 f m � /(Ljava/lang/Object;)Lkotori/gfx/KGLScriptModel;
 d } �D /efx/flash/glow01_yel3.ptc�            ?�  ?�  ?�               funnel_F_x2_endD /efx/flash/glow01_yel3.ptc�            ?   @   ?               D  /efx/kirakira/fairy_kirakira.scm�            @   @   @                kirakira2_yellowD /efx/flash/glow01_yel3.ptc�            >L��?�  >L��            D /efx/ripple/ripple05_yel3.ptc�            ?�  ?�  ?�              
 f u 1 
stopScript
 f � 1 f g  h  - 1  
� ��  i 1  c*� oW*� rL+� +� vW*d� wW*� yW*� |W*��� L*� �W*+� �W*�� wW*� oW=� *� W�W� ���  j 1  o*� �L+� �+� �=|�~>~6� � 
�� *� W�W*x�� |W* ȶ wW*�� �W*X� wW*� �W* ȶ wW� � *� W�  k 1 *� �L+� �=|�~>~6�W*x�� |W*� �:*� �:� �W� �� �� ��8*� �� �� ��8��� 
�f8��� 
�f8f8		��� 	�f8	� 	��� 
	�b8	� �8
� �� �8
� �� �� �Zd�8�� #bj8bj�� ��nj8b8�:� �:	� �W*� r:� � �W* ȶ wW* �b� �W* �� �W� ˶ �W*� �W*�Ӷ �W*� �W*�� wW6� *� W�W� `d����  l 1  c*� �W*� �W*ض �W*2� wW*� rL+� 
+ٶ �W*ڶ �W*�ܶ W*d� wW*ݶ �W*޶ �W*�� wW*� �W*� �W� m�X                        �  