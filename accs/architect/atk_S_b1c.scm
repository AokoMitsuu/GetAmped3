	� G   `�       �UK��0�ҕ��ݔ��4͂
=t�jy�<v������v�vw[��w�ΣiY�T�r>3��x<}�n�F9�mHN�1/iv�*V�������R*�V�Hł�J<���.��F��� #������8 �X�G��F��^Y���X�7e�Vt���������G=.3
ӷ�f_.ϓ�g��_��C��ͨ?��� Ls����o��T��ُK؄��]��i���f���D��k5�H�!ЩL���ꭡ��=6jM}�x`Q��0���� ���[=缗��*�"�bg��i���<�8� �����!IS= *]2CS3�,�U2��Ny?F��8�O 3��e9����uJ
��^|��]J}�����ޭ��Sa�e�$I�3�rj� \��%[�Н��{4���>�pu)���eߚߵ&u,SŲ�nJ��y�D��+������t�8��ZT������׷ǄA�z[H��(�٫(fO��&�+��^�piR�N������ '
`�2V�n:�UV�����0)�VfP�(ܩu��s̷�{QHb�ɘu�~J�2��&�ځ�	S��x6��kb��`��z?qFU�>���JX��6�ˏ��G�Nm�7��v�7^-ۿv����flIW�u؁0�l?
-�$�5��z�Z,����))�9)�{^X���ee�T���@:�dT�TοwA%�ٖv�E�;c���J@�N���w�ι�S�J����ڮ����]�p]
�jb��YE�%�5pN��� ���3E�.,}l|�n��������s���U�chU[��P$x�k<��B7�q�^V��>���XQBY�f�Cg�䊊j���Q�_R�{� @� �   j  Lga2/stage/AmpedScript; e Lkotori/data/MixtureData; mix�� kotori.data.MixtureData� /efx/mix/bar2.agi�L3 start approachtarget broken lockItemScript (ZZ)V
 f m n
 H 0 3 clearGenerate
 d q 1 generate A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d s t setGenerate 8(Lkotori/gfx/KGLScriptModel;)Lkotori/gfx/KGLScriptModel;
 d v wD /accs/architect/sign_S_b1c.scm�    ����    ?�  ?�  ?�               setJudgmentApproachTarget (F)V
 f z {@�   getGenerate (I)Lkotori/gfx/KGLScriptModel;
 d ~  Lkotori/gfx/KGLScriptModel; � startScript (Ljava/lang/String;)Z
 � � � loop clearJudgmentKind
 f � 1 lockTimeCount (Z)V
 f � � approach-             getArgv ()Ljava/lang/Object;
 f � �-             getPosition ()Lkotori/geom/Position;
 f � �  .(Lkotori/geom/Vector3D;)Lkotori/geom/Vector3D;
 � + � sub
 � � � Lkotori/geom/KMath; � getDir (Lkotori/geom/Vector3D;)F
 � � � R2D (F)F
 � � � ()F
 f � ��� kotori.geom.Quaternion?�               Lkotori/geom/Quaternion; � getHPBQuaternionD 5(FFFLkotori/geom/Quaternion;)Lkotori/geom/Quaternion;
 � � �� d�B�D #/accs/architect/iron_ball_S_b1c.scm�            ?�  ?�  ?�                 d�     d  �� 
 �       ,�>�        getAmpedModel ()Lga2/stage/AmpedModel;
 f � � MAT ()Lkotori/geom/Matrix;
 H � �  *(Lkotori/geom/Matrix;)Lkotori/geom/Matrix;
 � + � rotate .(Lkotori/geom/Quaternion;)Lkotori/geom/Matrix;
 � � � Lga2/stage/AmpedModel; � mat	 � �  setTranslate (FFF)Lkotori/geom/Matrix;
 � � �	 � Q 	 � R 	 � S  Lga2/stage/atk/AttackParam; � pmat	 � �  attack E(Lkotori/geom/Vector3D;Lga2/stage/atk/AttackParam;)Lga2/stage/Attack;
 f � �-    ?        exp kill
 f � 1 
stopScript
 f � 1 f h g - 1  i� �  j 1  R*� oW*d� pW*� oW*� rW**yj� u� xW*�� pW*}� |W*d� pW*� �� *� ��� �W�  k 1 *� �W*� oW*� �W*� pW*� �� *� ��� �W<� *d� pW�W���*� pW�M*� �N-� �N*� �:,-� �W,� �W,� �� �8*� �� �8f8�:� �W�:	*� �:

� 1� �:
� �� �� �W-� �-� �-� �� �W	� �*�	� �W*,� pW*� �� *� �ɶ �W*�� pW* ȶ pW*� �W*� �W�  l 1  9*� �W*� oW*� �W*� �� *� �ɶ �W* ȶ pW*� �W*� �W� �^�W            =L��>L��=L���  